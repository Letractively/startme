call mvn clean test checkstyle:checkstyle findbugs:findbugs cobertura:cobertura package -Dautoconfig.skip -Dmaven.test.failure.ignore=true
@pause