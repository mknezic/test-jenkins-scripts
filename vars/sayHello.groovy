def call(String name = 'human') {
    // Any valid steps can be called from this code, just like in other
    // Scripted Pipeline
    echo "Hello, ${name}."

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
