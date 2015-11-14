## Deploying a Play Application to Heroku using a PostgreSQL Database

### Setup

Before getting started deploying to Heroku make sure of the following:

1. Make sure you have a `.gitignore` file
  - `activator new` will create one for you. 
  - Alternatively, you can look at mine which adds a few more things [.gitignore]
2. Add your project to a git repository

  ```
  $ cd myproject
  $ git init
  $ git add .
  $ git commit -m "Initial Commit."
  ```
  
3. Make sure you have the [Heroku Toolbelt] installed and login once to set up

  ```BASH
  $ heroku login
  ```

### Configuring your Application for Heroku Deployment

When your applications run on Heroku, they run in a different **environment** from your local machine. The typical software deployment cycle has 3 phases: 1) Development 2) Staging 3) Production

When we are developing our application on our own machines and using `activator run` to run our application, we are in the Development environment. In this environment, the application provides detailed error messages that help us implement our applications.

When we deploy an application publicly, we are in a staging or production environment. In these environments we do not want our application to reveal code or backend information to the users, so the error messages become generic and we have to view log files to properly debug problems. Additionally, our application increases security measures which were not enforced during the Development phase.

__So what do we have to do to get our applications working in Heroku?__

1. Create an Application Secret
  - See instructions here: https://www.playframework.com/documentation/2.4.x/ApplicationSecret
  - or simply

    ```Bash
    $ activator playGenerateSecret
    [info] Loading project definition from /Users/medgardo/projects/movie-rate/project
    [info] Set current project to movie-rate (in build file:/Users/medgardo/projects/movie-rate/)
    [info] Generated new secret: 5z5M6f9WtHW=0xRq14tTQfq7Uwc3cu0@K6d[4BhA5@P2AAY0?Cwo7Z7nuQ6mhSVc
    [success] Total time: 0 s, completed Nov 13, 2015 10:37:30 PM
    ```

  - Copy the secret string (`5z5M6f9WtHW=0xRq14tTQfq7Uwc3cu0@K6d[4BhA5@P2AAY0?Cwo7Z7nuQ6mhSVc`) into your Heroku config vars. Follow instructions here: https://devcenter.heroku.com/articles/config-vars#setting-up-config-vars-for-a-deployed-application
  - Using the Dashboard Settings page is easier than the command line to set the variable correctly
  - I named the variable `APP_SECRET`
  - What is the application secret?
    - Think of it as a password that is used to sign your sessions. If you don't have it, sessions wont work. You should never store it in git, just like you would never save any (real) passwords in git or github repo.

2. Create a Procfile which provides sensitive runtime instructions (**NOTE:** Scroll to the right to see the complete command)

  ```
  web: target/universal/stage/bin/movie-rate -Dhttp.port=${PORT} -Dplay.evolutions.db.default.autoApply=true -Dplay.evolutions.db.default.autoApplyDowns=true -Ddb.default.url=${DATABASE_URL} -Dplay.crypto.secret=${APP_SECRET}
  ```
  - An explanation of the Procfile options:
    - `web: target/universal/stage/bin/movie-rate`
      - This tells heroku where to find the binary to start your application
      - **IMPORTANT**, `movie-rate` is my apps name. If your app name has capital letters, this file will be built in all lowercase letters. If you use uppercase letters here your app will fail to load!
    - `-Dhttp.port=${PORT}`
      - ${PORT} is calling the heroku environment variable for the port to run on. Don't change this.
    - `-Dplay.evolutions.db.default.autoApply=true -Dplay.evolutions.db.default.autoApplyDowns=true`
      - This tells your app to auto apply evolutions. The first part is for ups, or table creation. The second part is to run downs, or to drop tables. In production this second option is _harmful_, you will lose data. But for our ongoing development, it will ease our workflows.
    - `-Ddb.default.url=${DATABASE_URL}`
      - Gets the postgres url from Heroku directly.
    - `-Dplay.crypto.secret=${APP_SECRET}`
      - Gets the application secret directly from Heroku, which we saved in Step 1.
    - All of the above options override anything inside of `application.conf`. We never store production configuration in our git and github repositories. The safe practice is to pass these options through environment variables that are safely transferred to the server.

### Create a Heroku instance

Once you have the necessary configuration, you create a heroku instance *once* inside of your project directory:

```Bash
$ heroku create
```

### Deploying your Application

Anytime you're ready to push your master branch up to Heroku you run:

```Bash
$ git push heroku master
```

You can quickly open up a browser window by running:

```Bash
$ heroku open
```

### Additional commands

Checking Error Messages (*Tip:* leave this open as you visit your heroku site for live log details)
```Bash
$ heroku logs --tail
```

To get a Bash shell into your Heroku application
```Bash
$ heroku run bash
```

Opening a PostgreSQL command line:

```Bash
$ heroku pg:psql
```

### Reference Links

https://www.playframework.com/documentation/2.4.x/ApplicationSecret
https://www.playframework.com/documentation/2.4.x/ProductionConfiguration
https://www.playframework.com/documentation/2.4.x/ProductionHeroku
https://devcenter.heroku.com/articles/config-vars#setting-up-config-vars-for-a-deployed-application


[.gitignore]: .gitignore
[Heroku Toolbelt]: https://toolbelt.heroku.com/

