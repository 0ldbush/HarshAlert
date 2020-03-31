
lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.alnt",
  scalaVersion := "2.13.1",
  test in assembly := {}
)

scriptClasspath := Seq("*")  
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
    //mainClass in assembly := 
  )

lazy val utils = (project in file("utils")).
  settings(commonSettings: _*).
  settings(
   //assemblyJarName in assembly := alertPolicyEngine.jar
  )
  
lazy val root = (project in file(".")).
  enablePlugins(PlayJava,DockerPlugin,SwaggerPlugin).
  disablePlugins(PlayLogback).  
  settings(commonSettings: _*).
  settings(
    name := "alert-policy-engine",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots")
      //Resolver.sonatypeRepo("public")
    ),
	libraryDependencies ++= Seq(
	  evolutions,
	  javaJdbc,
	  guice,
	  caffeine,
	  //"com.h2database" % "h2" % "1.4.199",
	  "org.hibernate" % "hibernate-core" % "5.4.10.Final",
	  "com.palominolabs.http" % "url-builder" % "1.1.0",
	  "net.jodah" % "failsafe" % "2.3.1",
	  "org.jodd" % "jodd-bean" % "5.0.13",
	  "org.postgresql" % "postgresql" % "42.2.9",
	  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.0.jre8",
	  "org.springframework.data" % "spring-data-commons" % "2.2.4.RELEASE",
	  "org.owasp.esapi" % "esapi" % "2.2.0.0",
	  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.0.jre8",
	 "net.sourceforge.jtds" % "jtds" % "1.2", 
	  "commons-io" % "commons-io" % "2.6",
	  "commons-lang" % "commons-lang" % "2.6",
	  "commons-beanutils" % "commons-beanutils" % "1.9.4",
	  "org.apache.commons" % "commons-collections4" % "4.4",
	  
	  
	  "be.objectify" %% "deadbolt-java" % "2.8.1",
	  "com.auth0" % "java-jwt" % "3.9.0",
	  
	  //Annotation processors
	  "org.mapstruct" % "mapstruct" % "1.3.1.Final",
	  "org.mapstruct" % "mapstruct-processor" % "1.3.1.Final" ,
	  
	  //mvl
	  "org.mvel" % "mvel2" % "2.4.5.Final",
	  "com.google.code.gson" % "gson" % "2.8.5",
	  "org.assertj" % "assertj-core" % "3.12.2" % Test,
	  

	  "org.webjars" % "swagger-ui" % "2.2.0",
	 
	  
	  // https://mvnrepository.com/artifact/org.apache.poi/poi
      "org.apache.poi" % "poi" % "3.9",
      "com.monitorjbl" % "xlsx-streamer" % "0.2.3",
      
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.4.1",
      "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
      "org.apache.logging.log4j" % "log4j-core" % "2.4.1",

	  
	//json
	"com.jayway.jsonpath" % "json-path" % "2.4.0",
	
	"org.flywaydb" %% "flyway-play" % "6.0.0"
	
	
 	)
  )
  
import com.typesafe.sbt.packager.docker.DockerChmodType
dockerChmodType := DockerChmodType.UserGroupWriteExecute

swaggerDomainNameSpaces := Seq("com.alnt","api")
//playJava := true
swaggerPrettyJson := true

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case manifest if manifest.contains("MANIFEST.MF") =>
    // We don't need manifest files since sbt-assembly will create
    // one with the given settings
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    // Keep the content for all reference-overrides.conf files
    MergeStrategy.concat
  case x => MergeStrategy.concat
}

//PlayKeys.externalizeResources := false
PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"

// Compile the project before generating Eclipse files, so
// that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

// Java project. Don't expect Scala IDE
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

// Use .class files instead of generated .scala files for views and routes
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

javaOptions += "-Dlog4j.configurationFile=conf/log4j2.xml"

