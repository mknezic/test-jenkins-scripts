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

def type22 = params.typeOfBuild

sayHello(params.typeOfBuild)

doBuild {
    name = type22
}

runCodeQualityAnalysis {
    run = type22 == 'NIGHTLY'
}

runCodeQualityAnalysis {
    run = params.typeOfBuild == 'NIGHTLY'
}

```

so, cannot use params.typeOfBuild in closures as it won't be resolved as params is null, but can use that def type22