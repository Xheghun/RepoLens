## Repo Lens

A simple app that help users search github repos and Users, using
the [Github API](https://docs.github.com/en/rest?apiVersion=2022-11-28)

### Setup Up

to successfully build and run this project locally, you would need to

- create a `credentials.properties` file in the project root
- define your `githubaccessToken` value like this.

```properties
githubAccessToken="<YOUR-GITHUB-ACCESS-TOKEN>"
```

if you need help generating a github access token
this [doc](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic)
should help.

---

### Artifact

if you'd rather skip the steps above you can download the
apk [here](https://drive.google.com/file/d/1d9pBRcxas1fe0NffhMe88_0Ci7wfg6yt/view?usp=sharing)

---

### Architecture

This project follows the MVVM architecture pattern and clean code principles, ensuring a well
structured and maintainable codebase.

- Retrofit: handling Networking operations
- Coil: for image processing
- Koin: a simple lightweight library for dependency injection
- Mockito: to mock dependencies in Unit tests
- Androidx Splashscreen: a backward compatible splashscreen API

---

### Images

<img src="https://github.com/user-attachments/assets/c27919c6-142e-4264-bbcc-5885a93f5339" width =250, height=500>
<img src="https://github.com/user-attachments/assets/af753abb-1031-492e-9423-80e4971b6923" width =250, height=500>
<img src="https://github.com/user-attachments/assets/b0eed79e-60b3-41ce-94a8-805d4c96efbe" width =250, height=500>
<img src="https://github.com/user-attachments/assets/65afd988-1e03-4284-adf7-155873bb7145" width =250, height=500>
