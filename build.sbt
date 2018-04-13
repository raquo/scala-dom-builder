// @TODO[WTF] Why can't this be inside releaseSettings?
releaseCrossBuild := true

// @TODO[SBT] How to extract these shared settings into a separate release.sbt file?
val releaseSettings: Seq[Setting[_]] = Seq(
  name := "Scala DOM Builder",
  normalizedName := "dombuilder",
  organization := "com.raquo",
  scalaVersion in ThisBuild := "2.12.4", // @TODO[WTF] Why exactly do we need `in ThisBuild` here?
  crossScalaVersions in ThisBuild := Seq("2.11.11", "2.12.4"), // @TODO[WTF] Why exactly do we need `in ThisBuild` here?
  homepage := Some(url("https://github.com/raquo/scala-dom-builder")),
  licenses += ("MIT", url("https://github.com/raquo/scala-dom-builder/blob/master/LICENSE.txt")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/raquo/scala-dom-builder"),
      "scm:git@github.com/raquo/scala-dom-builder.git"
    )
  ),
  developers := List(
    Developer(
      id = "raquo",
      name = "Nikita Gazarov",
      email = "nikita@raquo.com",
      url = url("http://raquo.com")
    )
  ),
  sonatypeProfileName := "com.raquo",
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  releaseCrossBuild := true,
  pomIncludeRepository := { _ => false },
  useGpg := false,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value
)

lazy val root = project.in(file("."))
  .aggregate(js, jvm)
  .settings(releaseSettings)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val dombuilder = crossProject.in(file("."))
  .settings(releaseSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.raquo" %%% "domtypes" % "0.6"
    )
  )
  .jsConfigure(_.enablePlugins(ScalaJSBundlerPlugin))
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    requiresDOM in Test := true,
    useYarn := true,
    emitSourceMaps in fastOptJS := false,
    emitSourceMaps in fullOptJS := false,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.4",
      "org.scalatest" %%% "scalatest" % "3.0.4" % Test,
      "com.raquo" %%% "domtestutils" % "0.6" % Test
    )
  )
  .jvmSettings()

lazy val js = dombuilder.js
lazy val jvm = dombuilder.jvm
