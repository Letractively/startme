1、根据Appfuse原型建立Web项目。
mvn archetype:create -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-struts -DremoteRepositories=http://static.appfuse.org/releases -DarchetypeVersion=2.0.2 -DgroupId=com.yehongyu.webapp -DartifactId=webapp

2、下载代码
mvn appfuse:full-source