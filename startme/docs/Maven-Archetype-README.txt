Maven --如何生成工程原型 Archetype，并上传到仓库，根据原型创建工程。
项目中经常需要使用类似的开发模板，但是，每次都需要改很多的配置，才可以正常使用。可经利用Maven 的工程原型，制作项目的开发模板 Archetype。

方法：

1. 建立原型工程：调整好自己的可以作为模板的工程。

2. 生成原型模块：运行命令：mvn archetype:create-from-project ，这样就生成了工程原型的模板树。位于：target/generated-sources/archetype
或者根据原型创建原型mvn archetype:create -DgroupId=com.mycompany -DartifactId=my-archetype -DarchetypeArtifactId=maven-archetype-archetype

3. 安装原型至本地仓库：到target/generated-sources/archetype 目录下，运行mvn install ,即可生成工程原型的jar文件。

4. 使用本地仓库工程原型：已经可以使用了。是本地的工程原型。
运行下面的命令即可按此原型模板生成工程：mvn archetype:generate -DarchetypeCatalog=local
也可使用m2eclipse 按照普通的用法使用该原型新建Maven 工程即可。

5. 上传到远程仓库：修改archetype\pom.xml文件，加入发布远程仓库信息。然后 mvn deploy
<distributionManagement>
		<repository>
			<id>releases</id>
			<url>http://localhost:8080/nexus/content/repositories/releases</url>
		</repository>
		<snapshotRepository>
			<id>snapshots</id>
			<url>http://localhost:8080/nexus/content/repositories/snapshots</url>
		</snapshotRepository>
	</distributionManagement>
请确保settion.xml里配置了认证：
<server>
	<id>snapshots</id>
	<username>admin</username>
	<password>admin123</password>
</server>
<server>
	<id>releases</id>
	<username>admin</username>
	<password>admin123</password>
</server>

6. 根据上传的原型创建工程：
运行命令：
mvn archetype:generate -DarchetypeGroupId=com.taobao.webx3 -DarchetypeArtifactId=webxmulti-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.taobao.myproject -DartifactId=myproject
即可按所指定的原型及目标生成工程目录。也可手动指定原型库如下：
mvn archetype:generate -DarchetypeGroupId=com.taobao.webx3 -DarchetypeArtifactId=webxmulti-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.taobao.myproject -DartifactId=myproject -DarchetypeRepository=http://10.32.100.10:8080/nexus/content/groups/public
