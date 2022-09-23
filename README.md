# kodaka

## Getting Started

### Installation

`pom.xml`
```xml
 <repository>
  <id>github</id>
  <name>kodaka</name>
  <url>https://maven.pkg.github.com/2mug1/kodaka</url>
</repository>

<dependency>
  <groupId>net.iamtakagi</groupId>
  <artifactId>kodaka</artifactId>
  <version>1.0.3</version>
  <scope>compile</scope>
</dependency>
```

`build.gradle`
```gradle
repositories {
  maven (url = "https://maven.pkg.github.com/2mug1/kodaka")
}
dependencies {
  implementation("net.iamtakagi:kodaka:1.0.3")
}
```

### Usage

**Kotlin Example** - A command that broadcasts a message in chat (/broadcast \<message\>)
```kotlin
@CommandMeta(label = ["broadcast"])
class BroadcastCommand {

  fun execute(sender: CommandSender, message: String) {
    Bukkit.broadcastMessage("${ChatColor.AQUA}$message");
  }

}
```

**Java Example** - A command that broadcasts a message in chat (/broadcast \<message\>)
```java
@CommandMeta(label = "broadcast")
public class BroadcastCommand {

  public void execute(CommandSender sender, String message) {
    Bukkit.broadcastMessage(ChatColor.AQUA + message);
  }

}
```

**Result** (A kodaka automatically generates command usage and converts String[] arguments into a single String)
> ![img](https://i.gyazo.com/15f0fc1f1af2f49dda1571a1a80e31ce.gif)

## LICENSE
[MIT License](./LICENSE) (© 2022 iamtakagi)
