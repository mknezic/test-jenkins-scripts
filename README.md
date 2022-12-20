# test-jenkins-scripts
test scripts for jenkins pipelines

Example:
- define shared library test_libs_mk with https://github.com/mknezic/test-jenkins-scripts.git
- define new pipeline with provided sample script


sample pipeline script:
```groovy

@Library("test_libs_mk@master") _

properties([
        buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '30', daysToKeepStr: '', numToKeepStr: '30')),
        pipelineTriggers([
                [$class: 'ParameterizedTimerTrigger', parameterizedSpecification: '*/2 * * * * %typeOfBuild=NIGHTLY']
        ]),
        [$class: 'ParametersDefinitionProperty',
         parameterDefinitions: [
                 [$class: 'ChoiceParameterDefinition',
                  choices: 'DEFAULT\r\nNIGHTLY',
                  description: 'Type of build: DEFAULT or NIGHTLY.',
                  name: 'typeOfBuild']
         ]
        ]
])

doBuild {
    name = "${params.typeOfBuild}"
}

runCodeQualityAnalysis {
    run = params.typeOfBuild == 'NIGHTLY'
}

```