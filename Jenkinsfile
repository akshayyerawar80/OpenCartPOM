pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo "Building the project"
            }
        }
        stage("Run Unit test") {
            steps {
                echo "Running Unit Tests"
            }
        }
        stage("Run Integration test") {
            steps {
                echo "Running Integration Tests"
            }
        }
        stage("Deploy to dev") {
            steps {
                echo "Deploying to dev"
            }
        }
        stage("Deploy to QA") {
            steps {
                echo "Deploying to QA"
            }
        }
        stage("Run regression test cases on QA") {
            steps {
                echo "Run test cases on QA"
            }
        }
        stage("Deploy to stage") {
            steps {
                echo "Deploying to stage"
            }
        }
        stage("Run sanity test cases on QA") {
            steps {
                echo "Run sanity test cases on QA"
            }
        }
        stage("Deploy to PROD") {
            steps {
                echo "Deploying to PROD"
            }
        }
    }
}
