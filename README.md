# kodaka

## Getting Started

### Installation

`pom.xml`
```xml
 <repository>
  <id>github</id>
  <name>GitHub Packages</name>
  <url>https://maven.pkg.github.com/2mug1/kodaka</url>
</repository>

<dependency>
  <groupId>net.iamtakagi</groupId>
  <artifactId>kodaka</artifactId>
  <version>1.0.0</version>
</dependency>
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
