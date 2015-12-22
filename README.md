sbt-git-flow
============

Extension to the [sbt-release](https://github.com/sbt/sbt-release) plugin to make it 
compatible with [git flow](https://github.com/nvie/gitflow).

## Requirements
 * `git` and `git-flow`
 * sbt 0.13.5 or above.
 * Semantic version scheme [semver.org](http://www.semver.org) with the following additions:
   * The minor and bugfix part of the version are optional.
   * The appendix after the bugfix part must be alphanumeric (`[0-9a-zA-Z]`) but may also contain dash characters `-`.
   * These are all valid version numbers:
     * 1.2.3
     * 1.2.3-SNAPSHOT
     * 1.2beta1
     * 1.2
     * 1
     * 1-BETA17
 * A [publish repository](https://github.com/harrah/xsbt/wiki/Publishing) configured. 

## Usage
Add the following line to `./project/build.sbt`. 

    # project/plugins.sbt
    addSbtPlugin("com.servicerocket" % "sbt-git-flow" % "0.1.2")

Then you can release by using the following

    > sbt release

For anything other than the basic instructions provided here, refer to the 
[sbt-release](https://github.com/sbt/sbt-release) documentation.

### Including sbt-release settings
**Important:** The settings `releaseSettings` is automatically mixed into all projects,
 including sub-projects, as this is a sbt auto plugin. If you don't want it for some sub-projects you have to 
 explicitly disable it for them. See [Enabling and disabling auto plugins]
 (http://www.scala-sbt.org/0.13/tutorial/Using-Plugins.html#Enabling+and+disabling+auto+plugins) for more info. 

## Caveats
For some commands `git` sends the output to error stream which will cause some false negative exceptions
 to appear in the console. For now you can safely ignore this exceptions (for instance when a push to 
 remote happens), but in the future this can be improve by treat those commands differently or wait for this to be 
 fixed in `git.
 
Releasing
=========
This plugin is configured to be released to [ServiceRocket sbt plugins repo in bintray][1] which is a
[bintray][2] repository, under the name `sbt-plugins`, under the account `servicerocket`.

If you want to release this plugin you need the ServiceRocket account credentials and API key, so probably you
need to ask your administrator about it. Then you have to setup the credentials using the following `sbt` command:

    > sbt bintrayChangeCredentials

You only have to run this command once (more details on this [bintray sbt plugin docs][3]).
 
Now you can publish using thw following command (you have to reload your project at least once after setting up the
credentials):

    > sbt publish
    
Note that publishing snapshot versions is not allowed to [bintray][1]. If you need to publish and use a snapshot 
version in other projects, you can publish this project to your local [Ivy][4] repository using the following command:

    > sbt publish-local
 
### Other settings

This plugin is an extesntion to [sbt-release](https://github.com/sbt/sbt-release) so the settings supported by that plugin will be respected. 

For instance, you can run a non-interactive release by providing the argument `with-defaults` (tab completion works) to the `release` command. From command line use the follwoing command:

    > sbt 'release with-defaults'
    
  or alternatively from the `sbt` shell use the `release with-defaults` command.

You can also optionally avoid running any tests by providing the `skip-tests` argument to the `release` command:

    > sbt 'release skip-tests'

For more information please refer to [sbt-release](https://github.com/sbt/sbt-release).  

## License note
This plugin is a fork of `com.twitter:sbt-gitflow` plugin by `Sam Ritchie`.

[1]: https://dl.bintray.com/servicerocket/sbt-plugins/
[2]: https://bintray.com
[3]: https://github.com/softprops/bintray-sbt
[4]: http://ant.apache.org/ivy/
