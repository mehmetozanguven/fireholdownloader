# FireholDownloader: Utility library to download and check firehol ip list

fireholdownloader is a utility (easily customizable) Java library to download, load & check Firehol ip list.

> **Firehol IP list** objective is to create a blacklist that can be safe enough to be used on all systems, with a firewall, to block access entirely, from and to its listed IPs.
> 
> For more information [https://iplists.firehol.org/#about](https://iplists.firehol.org/#about)

## Release notes:

> Note: There are so many manual steps to send jar file to the maven-central. Unfortunately, I am giving up to send new releases to the maven-center. You may build the project code by yourself. Thanks..

- **v2.1.0**:
  - Update library dependencies to the latest versions:
    - Google-Guava v33.4.0-jre
    - Commons-net v3.11.1
    - Junit-Jupiter v5.11.4
    - Mockito v5.15.2
  
- **v2.0.0**:
  - Update to Java 17
  - Update version for commons-net 3.9.0 (v3.8.0 includes vulnerabilities)
  - Update versions for jupiter and mockito

## How to use fireholdownloader in your project

After imported the project via Maven (Gradle or using jar file):

```xml
<dependency>
  <groupId>io.github.mehmetozanguven</groupId>
  <artifactId>fireholdownloader</artifactId>
  <version>2.0.0</version> <!-- This is latest version you may fin on the maven-center -->
</dependency>
```

- Create `FireholDownloader` object with the builder:

```java
FireholDownloader fireholDownloader = new FireholDownloader.Builder().build();
```

- Initialize the downloader

```java
fireholDownloader.initializeDownloader();
```

- Then search the malicious ip address(es):

```java
// If ip is not found, then it will return null
FireholIpData fireholIpData = fireholDownloader.searchMaliciousIp("1.2.3.4");
```

## Customizing the fireholdownloader

Almost all fields in the builder is an interface, you may provide your interface to customize it.

### Customize the firehol directory set

By default, all firehol level set will be stored in the directory **/tmp/firehol**

If you want to change this, please write an implementation for `FireholDirectory`

> Default implementation is the `DefaultFireholDirectory`

```java
public class CustomizedFireholDirectory implements FireholDirectory {

    @Override
    public boolean createDirectoryIfNotExists() {
        return true;
    }

    @Override
    public String getDirectory() {
        return "/anotherFolder/subFolder";
    }
}
```

Then put your implementation into the Builder:

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .fireholDirectory(new CustomizedFireholDirectory())
                .build();
    }
}
```

### Customize the level set

Firehol IP provides four(4) level sets. If you don't want to download all level sets, you can create new implementation for `FireholAvailableLevelSets`

> Default implementation is the `DefaultFireholLevelSets`

For instance if you don't want to load level 3 set:

```java
public class CustomizedFireholLevelSets implements FireholAvailableLevelSets {
    @Override
    public FireholLevelSetInfo getLevel1SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level1.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level1.fll");
        return level;
    }

    @Override
    public FireholLevelSetInfo getLevel2SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level2.fll");
        return level;
    }

    @Override
    public FireholLevelSetInfo getLevel3SetInfo() {
        // Do not download level3 set
        return null; 
    }

    @Override
    public FireholLevelSetInfo getLevel4SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level4.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level4.fll");
        return level;
    }
}
```

Then put your implementation into the Builder:

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .fireholAvailableLevelSets(new CustomizedFireholLevelSets())
                .build();
    }
}
```

All ip sets are available in the `https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/...`, if you are not permitted to send any request **raw.githubusercontent**, then you can override the url address:

```java
public class CustomizedFireholLevelSets implements FireholAvailableLevelSets {

    @Override
    public FireholLevelSetInfo getLevel1SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://point/to/fireholSet");
        level.setLazyLoad(false);
        level.setFileName("firehol_level1.fll");
        return level;
    }
    // ...
}
```

You can also change file name for each level set:

```java
public class CustomizedFireholLevelSets implements FireholAvailableLevelSets {
    @Override
    public FireholLevelSetInfo getLevel1SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level1.netset");
        level.setLazyLoad(false);
        level.setFileName("another_name.extension");
        return level;
    }
    // ...
}
```

Especially level set 3 & 4 contain much more data, if you don't want to store all data in the memory, set `lazyLoad` attribute to `true`. For each malicious ip search, appropriate level set will be loaded from the file again and again.

### Customize the file retention time

Each time builder runs, it will check the last firehol file's creation date and if file is outdated it will download the ip set from the url

By default, retention time is `Duration.of(1, ChronoUnit.DAYS)`. You can customize the retention time:

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .fileRetentionTime(Duration.of( /*...*/))
                .build();
    }
}
```

### Always download new data

If you want to download new data from github for each run, then set the `alwaysLoadFromInternet` to true.

By default, this option is in false mode.

> Note: With this feature, all levels set will be store in the memory !! 

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .alwaysLoadFromInternet(true)
                .build();
    }
}
```

### File reader and writer

By default, fireholdownloader uses `ObjectInputStream` & `ObjectOutputStream` for writing and reading process. However, if you want to use your implementation, for instance you may want to store all files in .json file, please provide an implementation for `FireholFileWriterAndReader`:

> Default implementation is the `DefaultFireholFileWriterAndReader`


```java
public class CustomizedFireholFileWriterAndReader implements FireholFileWriterAndReader {
    // ...
}
```

Then put your implementation into the Builder:

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .fireholFileWriterAndReader(new CustomizedFireholFileWriterAndReader())
                .build();
    }
}
```

### HttpRequest.Builder & HttpClient.Builder

You can also provide your implementation using the interface `FireholHttpBuilder`

> Default implementation is the `DefaultFireholHttpBuilder`

```java
public class CustomizedFireholHttpBuilder implements FireholHttpBuilder {
    public static final Duration TIMEOUT = // custom timeout;

    @Override
    public HttpClient.Builder getHttpClientBuilder() {
        return HttpClient.newBuilder();
    }

    @Override
    public HttpRequest.Builder getHttpRequestBuilder() {
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .timeout(TIMEOUT)
                .GET()
                ;
    }
}
```

Then put your implementation into the Builder:

```java
class FireholDownloaderTest {
    public static void main(String[] args) {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .httpBuilder(new CustomizedFireholHttpBuilder())
                .build();
    }
}
```

---

There are also other fields (interfaces) such as response preparer, searching algorithm etc ... can be customized. Don't forget to look at the builder class