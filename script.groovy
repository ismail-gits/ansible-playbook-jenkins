def copyToAnsible() {
    echo "copying all necessary files to ansible control node server"

    sshagent(['ansible-server-key']) {
        sh "scp -o StrictHostKeyChecking=no ansible/* ec2-user@13.200.237.65:~/"

        withCredentials([sshUserPrivateKey(
            credentialsId: 'ec2-server-key',
            keyFileVariable: 'keyfile',
            usernameVariable: 'user'
        )]) {
                sh 'scp $keyfile ec2-user@13.200.237.65:~/ansible-jenkins.pem'
        }
    }
}

def executeAnsiblePlaybook() {
    echo "executing ansible playbook to configure ec2 instances"

    def remote = [:]
    remote.name = "ansible-server"
    remote.host = "13.200.237.65"
    remote.allowAnyHosts = true

    withCredentials([sshUserPrivateKey(
        credentialsId: 'ansible-server-key',
        keyFileVariable: 'keyfile',
        usernameVariable: 'user'
    )]) {
        remote.user = user
        remote.identityFile = keyfile

        sshCommand remote: remote, command: "ls -l"
    }
}

return this