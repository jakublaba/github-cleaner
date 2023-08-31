# GitHub cleaner

This application is used to make removing several repos at the same time more convenient
than having to click through everything on your GitHub.

## Usage

In order to have the app work properly, you have to acquire GitHub api access key and provide your credentials. \
In order to do that, place a `.env` file in the root of this project. \
Structure of the file is as follows:

```
GH_USERNAME=<your github username>
GH_AUTH_TOKEN=<your github api access token>
```

After that, adjust list of name of the repos you want to delete in the `repos` variable in `Main.java` file. \
It's a lazy approach, I know, maybe later I will improve this to read repos from some config file.
