def copyToAnsible() {
    echo "copying all necessary files to ansible control node server"

    sshagent(['ansible-server-key']) {
        sh "scp -o StrictHostKeyChecking=no ansible/* ec2-user@${ANSIBLE_SERVER}:~/"

        withCredentials([sshUserPrivateKey(
        credentialsId: 'ec2-server-key',
        keyFileVariable: 'keyfile',
        usernameVariable: 'user'
        )]) {
            sh 'scp $keyfile ec2-user@$ANSIBLE_SERVER:~/ansible-jenkins.pem'
        }
    }
}

def executeAnsiblePlaybook() {
    echo "executing ansible playbook to configure ec2 instances"

    def remote = [:]
    remote.name = "ansible-server"
    remote.host = env.ANSIBLE_SERVER
    remote.allowAnyHosts = true

    withCredentials([sshUserPrivateKey(
        credentialsId: 'ansible-server-key',
        keyFileVariable: 'keyfile',
        usernameVariable: 'user'
    )]) {
        remote.user = user
        remote.identityFile = keyfile

        sshCommand remote: remote, command: "ls -l"
        sshCommand remote: remote, command: "ansible-playbook playbook.yaml"
    }
}

return this