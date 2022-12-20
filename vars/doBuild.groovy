def call(body) {
    Map jenkinsfileConfig = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = jenkinsfileConfig
    body()

    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${jenkinsfileConfig.name} 2."

    stage "test node call2"
    doNodeJob2("jobBlaX2")

    stage "something2"
    echo "do something2"

}

def doNodeJob2(String jobName = 'jobx') {
    node() {
        echo "job2 name is " + jobName

        stage("other 4") {
            sh "uname -a"
            sh "df -h"
        }

        stage "other 5"
        echo "done with doNodeJob2"
    }
    node() {
        stage "nodeX"
        echo "finish nodeX"
    }
}
