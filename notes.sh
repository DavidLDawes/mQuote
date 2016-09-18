#! /bin/bash 
npm install tough-cookie@latest minimatch@latest lodash@latest graceful-fs@latest cross-spawn@latest
npm uninstall marked
sudo npm install -g marked@latest
npm uninstall npmconf
#
# fixes for thje following warnings when I ran mvn on 9/16
#npm WARN deprecated minimatch@2.0.10: Please update to minimatch 3.0.2 or higher to avoid a RegExp DoS issue
#npm WARN deprecated minimatch@0.2.14: Please update to minimatch 3.0.2 or higher to avoid a RegExp DoS issue
#npm WARN deprecated lodash@1.0.2: lodash@<3.0.0 is no longer maintained. Upgrade to lodash@^4.0.0.
#npm WARN deprecated graceful-fs@1.2.3: graceful-fs v3.0.0 and before will fail on node releases >= v7.0. Please update to graceful-fs@^4.0.0 as soon as possible. Use 'npm ls graceful-fs' to find it in the tree.
#npm WARN deprecated cross-spawn-async@2.2.4: cross-spawn no longer requires a build toolchain, use it instead!
#npm WARN deprecated minimatch@0.3.0: Please update to minimatch 3.0.2 or higher to avoid a RegExp DoS issue
#npm WARN prefer global marked@0.3.6 should be installed with -g

