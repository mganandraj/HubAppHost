# Integrate with Teams
Deployment key via ECS will be used to download RN App bundle. If ECS flag does not exists, Deployment key from rnUrl in App Definition will be used as fallback.

## Integrate via App Definition
Update `rnPackageUrl` in app definition to:
`codepush://`{packageName}`/?deploymentKey=`{deploymentKey}

For Example:
```codepush://teams-mobile-sdk-example/?deploymentKey=_uVIKMilIBiQTiFhfURMqHy4G7S3ryjiyjgWE```

## Integrate via ECS
### Configuration parameters format
```
{
  "platform": {
    "<app_id>": {
      "enabled": "true",
      "deploymentkey": "<deployement_key>"
    }
  }
}
```

### For Android
* Create new feature rollout similar to [org chart](https://ecs.skype.com/?page=ExperimentPage&type=Rollout&id=26471).
* Update parameters as required, including `configuration parameter`.
* Publish

### For iOS
* Create new feature rollout similar to [org chart](https://ecs.skype.com/?page=ExperimentPage&type=Rollout&id=27106).
* Update parameters as required, including `configuration parameter`.
* Publish
