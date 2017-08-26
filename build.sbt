
name := "Scala DOM Builder"

scalaVersion in ThisBuild := "2.11.11" // "in ThisBuild" also applies this setting to JS and JVM projects

lazy val root = project.in(file("."))
  .aggregate(js, jvm)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val dombuilder = crossProject.in(file("."))
  .settings(
    organization := "com.raquo",
    normalizedName := "dombuilder",
    version := "0.1-SNAPSHOT",
    crossScalaVersions := Seq("2.11.11", "2.12.3"),
    homepage := Some(url("https://github.com/raquo/scala-dom-builder")),
    licenses += ("MIT", url("https://github.com/raquo/scala-dom-builder/blob/master/LICENSE.txt")),
    libraryDependencies ++= Seq(
      "com.raquo" %% "domtypes" % "0.1-SNAPSHOT"
    )
  )
  .jsConfigure(_.enablePlugins(ScalaJSBundlerPlugin))
  .jsSettings(
    requiresDOM in Test := true,
    useYarn := true,
    emitSourceMaps in fastOptJS := false,
    emitSourceMaps in fullOptJS := false,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "com.raquo" %%% "domtypes" % "0.1-SNAPSHOT",
      "org.scalatest" %%% "scalatest" % "3.0.3" % Test,
      "com.raquo" %%% "domtestutils" % "0.1-SNAPSHOT" % Test
    )
  )
  .jvmSettings()

lazy val js = dombuilder.js
lazy val jvm = dombuilder.jvm
