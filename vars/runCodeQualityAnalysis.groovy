def call(body) {
    Map jenkinsfileConfig = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = jenkinsfileConfig
    body()
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    if (jenkinsfileConfig.run) {
        echo "Will run analysis."
        stage "code quality analysis"
        doNodeJob("runCodeQualityAnalysis")
    } else {
        echo "Hello, I will skip runnnig analysis as it is marked as run=${jenkinsfileConfig.run}."
    }


    stage "test node call"
    doNodeJob("jobBlaX")

    stage "something"
    echo "do something"

}

def doNodeJob(String jobName = 'jobx') {
    node() {
        echo "job name is " + jobName
    }
}
