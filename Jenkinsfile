#!/usr/bin/env groovy

def gv

pipeline {
    agent any

    environment {
        ANSIBLE_SERVER = "13.200.237.65"
    }
    
    stages {
        stage('init') {
            steps {
                script {
                    echo "loading script.groovy"
                    gv = load "script.groovy"
                }
            }
        }

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