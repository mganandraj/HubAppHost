# Contributor guidelines

## Unit tests
All new code must have unit tests. Refert to __tests__/README.md for more info.

## Documentation
We are using `typedoc`, `type2docfx` and `docfx` for documentation generation.

###Install docfx
docfx can be installed via Homebrew or Chocolatey:

```
brew install docfx
```

```
choco install docfx
```

For more info on installation refer to: https://dotnet.github.io/docfx/tutorial/docfx_getting_started.html.

### Install typedoc and type2docfx
Just run `yarn install` in `teams-mobile-sdk`.

### Adding documentation
Please write documentation comments for all new entities (classes, members etc) that you introduce. After you have added
the required documentation you need to regenerate doc files to pick up the latest changes in your source files:

```
yarn docfx
```

If you are writing a big feature that requires an article in our documentation follow these steps for adding it:
1. Write your article in markdown and place it in `docfx/articles/`
2. Add an entry for your article in `docfx/articles/toc.yml`

### Viewing documentation locally
Run these commands to view documentation locally:
```
cd teams-mobile-sdk
docfx docfx/docfx.json // will generate the site in _site folder
docfx serve docfx/_site // will host the _site folder in a local server
```
The documentation site is now being served at http://localhost:8080.

### Publishing documentation
Documentation publishing is automated. The site is updated whenever new code is
checked-in to develop.

Pipelines:
Build: https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_apps/hub/ms.vss-ciworkflow.build-ci-hub?_a=edit-build-definition&id=1384
Release: https://domoreexp.visualstudio.com/DefaultCollection/Teamspace/_releaseDefinition?definitionId=1406&_a=definition-pipeline

Azure resource details:
Subscription: msteams.nonprod.pub.msft.mobile
Resource group: rn_sdk
Web App name: teamsmobilesdk

https://ms.portal.azure.com/#@microsoft.onmicrosoft.com/resource/subscriptions/d3698026-48a2-4e06-b722-42bbb19b8ee0/resourceGroups/rn_sdk/providers/Microsoft.Web/sites/teamsmobilesdk/appServices