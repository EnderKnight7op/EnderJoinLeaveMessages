# EnderJoinQuitMessages

A lightweight Paper plugin for customising player join and leave messages.

## Features

* Customisable join messages
* Customisable leave messages
* Simple YAML configuration
* Reload configuration without restarting the server
* Lightweight with minimal overhead

## Requirements

* Paper 1.21+
* Java 25+

## Installation

1. Download the latest release from the [Releases](../../releases) page.
2. Place the `.jar` file in your server's `plugins` directory.
3. Start or restart your server.
4. Configure the plugin using `config.yml`.

## Configuration

After the first startup, the configuration file can be found at:

```text
plugins/EnderJoinQuitMessages/config.yml
```

Example:

```yaml
join-message: "&a+ &f%player% joined the server!"
leave-message: "&c- &f%player% left the server!"
```

### Reloading

After making changes to the configuration, use:

```text
/ejqm reload
```

to apply them without restarting the server.

## Building

This project uses Gradle with Kotlin DSL.

To build the plugin from source, use the included Gradle wrapper.

### Windows

```powershell
.\gradlew.bat build
```

### Linux / macOS

```bash
./gradlew build
```

The compiled plugin will be available in:

```text
build/libs/
```

## Contributing

Contributions, suggestions, and bug reports are welcome.

If you find an issue, please open an issue on GitHub with as much relevant information as possible.

## Licence

This project is licensed under the MIT License.

See the [LICENSE](LICENSE) file for the full licence text.

## Community

For support, questions, suggestions, or discussion, join the Ender Labs Discord server:

**[Join the Ender Labs Discord](https://dsc.gg/enderlabs)**


## Author

**EnderKnight7op**

EnderJoinQuitMessages was created as a first Minecraft plugin while learning Java and Paper plugin development.
