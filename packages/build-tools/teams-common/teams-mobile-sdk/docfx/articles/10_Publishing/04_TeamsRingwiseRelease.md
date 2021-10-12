# Teams Ring-wise Release

Teams supports Feature Rollout to different rings using ECS. React native platform also supports releasing a RN app to these rings.

## Steps to configure environment:
### To achieve
    Ring 0 - Bundle v5
    Ring 1 - Bundle v4
    Ring 2 - Bundle v3
    Ring 3 - Bundle v2
    Ring 4 - Bundle v1
### Step 1 : Create 5 environments on Codepush for each ring. Say :
    Ring 0 - env_r0 (deploymentkey : dk0)
    Ring 1 - env_r1 (deploymentkey : dk1)
    Ring 2 - env_r2 (deploymentkey : dk2)
    Ring 3 - env_r3 (deploymentkey : dk3)
    Ring 4 - env_r4 (deploymentkey : dk4)
### Step 2 : Upload all bundles in env_r0 and promote them to next ring as required. Finally, it will look something like :
    Ring 0 - Bundle v1, Bundle v2, Bundle v3, Bundle v4, Bundle v5
    Ring 1 - Bundle v1, Bundle v2, Bundle v3, Bundle v4 
    Ring 2 - Bundle v1, Bundle v2, Bundle v3
    Ring 3 - Bundle v1, Bundle v2
    Ring 4 - Bundle v1
### Step 3 : Create new Feature Rollout with 5 features with following configuration :
* Priority 1  
    - deploymentkey : dk0
    - 100% allocaltion - Ring 0
* Priority 2  
    - deploymentkey : dk1
    - 100% allocaltion - Ring 1
* Priority 3  
    - deploymentkey : dk2
    - 100% allocaltion - Ring 2
* Priority 4  
    - deploymentkey : dk3
    - 100% allocaltion - Ring 3
* Priority 5  
    - deploymentkey : dk4
    - 100% allocaltion - Ring 4
* Json :
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
