#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    
    stages {
        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
                     echo "Executing pipeline for branch $BRANCH_NAME"
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