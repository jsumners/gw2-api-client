# GW2-API-Client

***GW2-API-Client*** is a Java library that provides a simple way to interact
with the [Guild Wars 2 API](http://wiki.guildwars2.com/wiki/API:Main). This
library was written because I, [James Sumners](http://jrfom.com/), found the
existing Java libraries either unmaintained, incomplete, or didn't work in a
way I liked.

This library hopes to meet the following goals:

1. Be easy to use
2. Be comprehensive
3. Have minimal [dependencies](http://jsumners.github.io/gw2-api-client/maven-site/0.1/dependencies.html)

In regard to #3, I know that [Spring](http://spring.io/) is not a lightweight
dependency. I started out with the intention of using [Restlet](http://restlet.org/)
as the REST client library, but it did not work out. Something with the way
Restlet uses the [Jackson](http://jackson.codehaus.org/Home) library caused
Jackson to ignore the annotations this library relies upon to handle the GW2
API's JSON responses. Since Restlet's documentation is worse than Spring's
(which is a feat), I did not waste my time figuring that problem out. Thus, the
dependency on Spring (as little of it as possible, though).

## Quick Usage Demonstration

```java
import com.jrfom.gw2.ApiClient;
import com.jrfom.gw2.api.model.Build;

public class Demo {
  public static void main(String[] args) {
    ApiClient apiClient = new ApiClient();
    Build build = apiClient.getBuild();

    System.out.println("Build number: " + build.getBuildId());
  }
}
```

Other examples are available by inspecting the
[client unit tests](http://jsumners.github.io/gw2-api-client/maven-site/0.1/xref-test/com/jrfom/gw2/package-summary.html).

## Installation

This library is distributed through [Maven](http://maven.apache.org/). You can
add it to your project's pom.xml by adding the following repositories and
dependency:

```xml
<dependencies>
  <dependency>
    <groupId>com.jrfom</groupId>
    <artifactId>GW2-API-Client</artifactId>
    <version>0.1</version> <!-- or whatever version you desire -->
  </dependency>
</dependencies>

<repositories>
  <repository>
    <id>jsumners-github-releases</id>
    <url>https://github.com/jsumners/mvn-repo/raw/master/releases/</url>
  </repository>
  <repository>
    <id>jsumners-github-snapshots</id>
    <url>https://github.com/jsumners/mvn-repo/raw/master/snapshots/</url>
  </repository>
</repositories>
```

Other than that, you can download the source code from this Github repository
and build it yourself.

## Documentation

At this time, the only documentation available is the
[generated documentation](http://jsumners.github.io/gw2-api-client/maven-site/0.1/apidocs/index.html).
However, it is very complete and should be easy to use.

The is also the Maven generated
[project website](http://jsumners.github.io/gw2-api-client/maven-site/0.1/).

## Library API Stability

It is possible that this library's API may have to change. It is designed to be
a stable API, but, at the time of this writing, it has not been used in an actual
project. Issues may come to light that need to be corrected that could cause a
change in this library's API.

Also, ArenaNet has not stabilized their API. So this library's API could have to
change due to an upstream change.

However, all such changes will be clearly illustrated when they occur.

## Deserialization

***GW2-API-Client*** goes to great lengths to deserialize the GW2 API's JSON in
as intuitive way as possible. For example, if the API method is "/v1/foobar.json"
and returns:

```json
{
  "foobar": {
    "1": {...},
    "2": {...},
    "3": {...}
  }
}
```

Then this library will decode the JSON to an array list of objects with the
numeric keys added to the respective objects as an identifier property. A good
example is the [Colors](http://wiki.guildwars2.com/wiki/API:1/colors) API
method. This is implemented in ***GW2-API-Client*** as
[ColorsList](http://jsumners.github.io/gw2-api-client/maven-site/0.1/apidocs/com/jrfom/gw2/api/model/colors/ColorsList.html).

Additionally, instances where the GW2 API returns integers as strings get
automatically converted to real integers (or similar) by ***GW2-API-Client***.

## Serialization

***GW2-API-Client*** includes support for serializing each API representation
back to JSON. That is, an [item details](http://wiki.guildwars2.com/wiki/API:1/item_details)
object will serialize to JSON that matches the original JSON returned by the GW2
API (though, not necessarily with the same key ordering).

There is one difference, though. ***GW2-API-Client*** does not reserialize
integers to strings in any case. It will always serialize a integer to an integer.
Further, in cases where this library opts to use a double to represent a numeric
value, then the serialization will be a JSON float value.

## License

The MIT License (MIT)

Copyright (c) 2014 James Sumners

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.