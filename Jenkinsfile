pipeline{
    
    agent any
    
    stages{
    
        stage("Build"){
            steps{
                echo("build the project")
            }
        }



         stage("Deploy to dev"){
            steps{
                echo("deploying to dev env")
            }
        }



        stage("Deploy to qa"){
            steps{
                echo("deploying to qa env")
            }
        }

        stage("Run integration automation test - QA"){
                    steps{
                        echo("Run integration automation test cases")
                    }
                }


        stage("Run regression automation test cases"){
            steps{
                echo("Run regression automation test cases")
            }
        }

        stage("Deploy to stage"){
            steps{
                echo("deploying to stage env")
            }
        }

           stage("Run integration automation test - Stage"){
                            steps{
                                echo("Run integration automation test cases in stage env")
                            }
                        }

        stage("Run sanity automation test cases"){
            steps{
                echo("Run sanity automation test cases")
            }
        }

        
    }

}