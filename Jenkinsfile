#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    
    stages {
        stage("copy to ansible server") {
            steps {
                script {
                    echo "copying all necessary files to ansible control node server"
                    sshagent(['ansible-server-key']) {
                        sh "scp -o StrictHostKeyChecking=no ansible/* ec2-user@13.200.237.65:~/"

                        withCredentials([
                            sshUserPrivateKey(
                                credentialsId: 'ec2-server-key',
                                keyFileVariable: 'keyfile',
                                usernameVariable: 'user'
                            ) {
                                sh "scp ${keyfile} ec2-user@13.200.237.65:~/ansible-jenkins.pem"
                            }
                        ])
                    }
                }
            }
        }
    }
}