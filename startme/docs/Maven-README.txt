Maven核心：pom.xml文档结构说明和settings.xml环境配置说明。
1、Maven生命周期-Phase:命令eg:[mvn clean]不带:号。Phase也是由内部Plugins完成的（Yes）。
 生命周期lifecycle，一个项目在maven中有三个生命周期，分别为default,clean,site。default主要是编译和部署生命周期，clean是清理生命周期，site是生成报表、工程文档等生命周期。
 生命周期中阶段phase。如default的生命周期中拥有validate、initialize、compile、test-compile、prepare-package、package、install、deploy等等的阶段。阶段是顺序执行的。
Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy.

以下未整理：
mvn compile 【编译工程 ，生命周期默认绑定了插件命令，相当于执行插件compile:compile，并会执行之前的生命周期插件目标】
mvn clean 清理生成的文件， 一般与package install 等命令一起使用 
mvn test 测试src/main/test下的文件 
mvn package 将整个工程打包 
mvn install 将工程打包并部署到本地仓库中 
mvn deploy 将工程打包并部署到远程仓库中 
mvn site   生成项目相关信息的网站 
mvn validate        验证工程是否正确，所有需要的资源是否可用。 
mvn test-compile    编译项目测试代码。 。 
mvn integration-test     在集成测试可以运行的环境中处理和发布包。 
mvn verify        运行任何检查，验证包是否有效且达到质量标准。     
mvn generate-sources    产生应用需要的任何额外的源代码，如xdoclet。

2、Maven执行目标-Goals:命令eg:[mvn clean:clean]带:号。Goals也是都是Plugins完成的（Yes）。
goal 阶段中的目标。每一个阶段中，蕴含着一个或多个目标。
mojo 执行目标的具体代类
目标命令执行格式有两种：
1、mvn {goalPrefix}:{goal}	
命令说明：goalPrefix表示执行插件目标的命令前缀，goal表示插件的具体执行目标。
这是在Maven官方中的命令执行模式。
2、如果不是则需要指定全名格式：mvn {groupId}:{artifactId}:{version}:{goal}
例如：mvn com.yehongyu.plugins:maven-gencode-plugin:1.0-SNAPSHOT:sayhi
或者在pom.xml中指定Build插件的引用，也可以用第一种模式。

3、Maven常用插件：插件可以包含在Phase里执行，也可以指定Goals执行。
http://www.infoq.com/cn/news/2011/04/xxb-maven-7-plugin
插件有两种：buildPlugins和reportPlugins.

Maven官方有两个插件列表：
第一个列表的GroupId为org.apache.maven.plugins，这里的插件最为成熟，具体地址为：http://maven.apache.org/plugins/index.html。
maven-antrun-plugin
maven-archetype-plugin
maven-assembly-plugin
maven-dependency-plugin
maven-enforcer-plugin
maven-help-plugin
maven-release-plugin

第二个列表的GroupId为org.codehaus.mojo，这里的插件没有那么核心，但也有不少十分有用，其地址为：http://mojo.codehaus.org/plugins.html。
exec-maven-plugin


5、Maven常用命令、生命周期命令及插件命令：
mvn -v 显示版本 
mvn -e 显示详细错误 信息. 
mvn package -Dmaven.test.skip=true 【给生命周期的目标添加maven.test.skip 属性就能跳过测试 】
-DdownloadSources=true  【给生命周期的目标添加downloadSources 属性就能下载Sources？ 】
-DdownloadJavadocs=true 【给生命周期的目标添加downloadSources 属性就能下载Javadocs？ 】
-DfailIfNoTests=false 【给生命周期的目标添加downloadSources 属性就能在没有测试用例是不提示失败 】
-Dautoconfig.userProperties=./antxme.properties【给指定的插件指定参数】
mvn test -skipping compile -skipping test-compile 【只测试而不编译，也不测试编译：-skipping 的灵活运用，当然也可以用于其他组合命令】
mvn install -X 【想要查看完整的依赖踪迹，包含那些因为冲突或者其它原因而被拒绝引入的构件，打开 Maven 的调试标记运行】
mvn clean install 【删除再编译  ，组合命令】

安装指定包到本地仓库：mvn install:install-file -DgroupId=com -DartifactId=client -Dversion=0.1.0 -Dpackaging=jar -Dfile=d:\client-0.1.0.jar 
安装指定包到远程仓库：mvn deploy:deploy-file -DgroupId=com -DartifactId=client -Dversion=0.1.0 -Dpackaging=jar -Dfile=d:\client-0.1.0.jar -DrepositoryId=maven-repository-inner -Durl=ftp://xxxxxxx/opt/maven/repository/ 

mvn archetype:create -DgroupId=com.oreilly -DartifactId=my-app   【创建mvn项目，已废弃，使用archetype:generate代替】
mvn archetype:generate -DarchetypeGroupId=com.taobao.webx3 -DarchetypeArtifactId=webx3-multi-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.taobao.myproject -DartifactId=myproject -DarchetypeRepository=http://10.32.100.10:8080/nexus/content/groups/public【根据原型创建Maven项目，-DarchetypeRepository指定原型的来源仓库，不指定使用settings.xml设置的仓库】
mvn archetype:create -DgroupId=com.mycompany -DartifactId=my-archetype -DarchetypeArtifactId=maven-archetype-archetype【根据原型创建原型】
mvn archetype:create-from-project 【生成工程原型的模板树。位于：target/generated-sources/archetype】
mvn archetype:generate -DarchetypeCatalog=local【根据本地生成的原型生成工程】

mvn eclipse:clean 【清除eclipse工程文件配置信息 】
mvn eclipse:eclipse 【生成eclipse工程文件配置信息 】
mvn eclipse:clean -Dwtpversion=1.0 【清除Wtp插件的Web项目配置信息】
mvn eclipse:eclipse -Dwtpversion=1.0  【生成Wtp插件的Web项目配置信息 】

mvn idea:idea 【 生成idea项目】

mvn help:describe -Dplugin=com.yehongyu.plugins:maven-gencode-plugin -Dmojo=sayhi -Dfull【查看指定插件的的信息。-Dplugin指定要查看的插件；-Dfull参数表示输出完整的带参数的插件Goal信息；-Dmojo参数指定具体的Goal,不写输出所有Goal。】
mvn help:effective-pom【看这个“有效的 (effective)”POM，它暴露了 Maven的默认设置】
mvn help:effective-settings 【运行时使用setting文件 】

mvn dependency:resolve 【打印出已解决依赖的列表】 
mvn dependency:tree 【查看依赖树，可以分析出间接依赖关系 】
mvn dependency:analyze 【查看工程所依赖的插件，进行pom优化时可以用到】 
mvn dependency:sources 【自动寻找并下载jar包的源码 】

mvn assembly:assembly 【生成一个单独的可运行的jar包】

mvn exec:java -Dexec.mainClass=com.yehongyu.gencode.Xxx 【运行指定的应用-Dexec.mainClass指定执行的类其中包含main函数】


mvn jetty:run 【启动jetty容器，可以在测试时代替tomcat 】
mvn tomcat:run 【启动tomcat容器 】
mvnDebug tomcat:run 【可以在eclipse中设置断点进行调试 】

7、Maven原型创建
见mvn archetype插件。
mvn archetype:create -DgroupId=com.mycompany -DartifactId=my-archetype -DarchetypeArtifactId=maven-archetype-archetype【根据原型创建原型】
mvn archetype:create-from-project 【生成工程原型的模板树。位于：target/generated-sources/archetype】


8、Maven插件创建
mvn archetype:generate \
  -DgroupId=sample.plugin \
  -DartifactId=hello-maven-plugin \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-mojo
插件的执行（和官方的Goals类似）：mvn groupID:artifactID:version:goal