#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    
    stages {
        stage("copy to ansible server") {
            steps {
                script {
                    gv.copyToAnsible()
                }
            }
        }

        stage("execute ansible playbook") {
            steps {
                script {
                    gv.executeAnsiblePlaybook()
                }
            }
        }
    }
}