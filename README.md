# Email-Validator

This application receives list of emails, validates them against their syntax as
well as their domain name, classifies and eventually writes them into output files.

This application is compliant to [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html), according to [CheckStyle-IDEA](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea).

A comprehnesive JavaDoc was prepared for this project. Meanwhile, this project inludes 21 unit tests which pass all of them. These, in detail, can be found in [Documentation](./Documentation) folder.

# How to use?

Write your to-be-classified emails in the userInputs.txt file located at ```./src/main/resources```. Each line should have only email. After a successful run, the app creates three output files (as text) located at ```./target``` 

For sake of clarity, I have put a list of emails in 
[userInputs](./Project/src/main/resources/userInputs.txt), built and ran the application. So, you can see the [output results](./Project/target)
