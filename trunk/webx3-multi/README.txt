Maven --������ɹ���ԭ�� Archetype�����ϴ����ֿ⣬����ԭ�ʹ������̡�
��Ŀ�о�����Ҫʹ�����ƵĿ���ģ�壬���ǣ�ÿ�ζ���Ҫ�ĺܶ�����ã��ſ�������ʹ�á��ɾ�����Maven �Ĺ���ԭ�ͣ�������Ŀ�Ŀ���ģ�� Archetype��

������

1. ����ԭ�͹��̣��������Լ��Ŀ�����Ϊģ��Ĺ��̡�

2. ����ԭ��ģ�飺�������mvn archetype:create-from-project �������������˹���ԭ�͵�ģ������λ�ڣ�target/generated-sources/archetype
���߸���ԭ�ʹ���ԭ��mvn archetype:create -DgroupId=com.mycompany -DartifactId=my-archetype -DarchetypeArtifactId=maven-archetype-archetype

3. ��װԭ�������زֿ⣺��target/generated-sources/archetype Ŀ¼�£�����mvn install ,�������ɹ���ԭ�͵�jar�ļ���

4. ʹ�ñ��زֿ⹤��ԭ�ͣ��Ѿ�����ʹ���ˡ��Ǳ��صĹ���ԭ�͡�
�������������ɰ���ԭ��ģ�����ɹ��̣�mvn archetype:generate -DarchetypeCatalog=local
Ҳ��ʹ��m2eclipse ������ͨ���÷�ʹ�ø�ԭ���½�Maven ���̼��ɡ�

5. �ϴ���Զ�ֿ̲⣺�޸�archetype\pom.xml�ļ������뷢��Զ�ֿ̲���Ϣ��Ȼ�� mvn deploy
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
��ȷ��settion.xml����������֤��
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

6. �����ϴ���ԭ�ʹ������̣�
�������
mvn archetype:generate -DarchetypeGroupId=com.taobao.webx3 -DarchetypeArtifactId=webx3-multi-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.taobao.myproject -DartifactId=myproject
���ɰ���ָ����ԭ�ͼ�Ŀ�����ɹ���Ŀ¼��Ҳ���ֶ�ָ��ԭ�Ϳ����£�
mvn archetype:generate -DarchetypeGroupId=com.taobao.webx3 -DarchetypeArtifactId=webx3-multi-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.taobao.myproject -DartifactId=myproject -DarchetypeRepository=http://10.32.100.10:8080/nexus/content/groups/public

ps:����AppFuse����http://www.appfuse.org/display/APF/AppFuse+QuickStart