# GW2-API-Client

***GW2-API-Client*** is a Java library that provides a simple way to interact
with the [Guild Wars 2 API](http://wiki.guildwars2.com/wiki/API:Main). This
library was written because I, [James Sumners](http://jrfom.com/), found the
existing Java libraries either unmaintained, incomplete, or didn't work in a
way I liked.

This library hopes to meet the following goals:

1. Be easy to use
2. Be comprehensive
3. Have minimal dependencies

In regard to #3, I know that [Spring](http://spring.io/) is not a lightweight
dependency. I started out with the intention of using [Restlet](http://restlet.org/)
as the REST client library, but it did not work out. Something with the way
Restlet uses the [Jackson](http://jackson.codehaus.org/Home) library caused
Jackson to ignore the annotations this library relies upon to handle the GW2
API's JSON responses. Since Restlet's documentation is worse than Spring's
(which is a feat), I did not waste my time figuring that problem out. Thus, the
dependency on Spring (as little of it as possible, though).

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

    {
      "foobar": {
        "1": {...},
        "2": {...},
        "3": {...}
      }
    }

Then this library will decode the JSON to an array list of objects with the
numeric keys added to the respective objects as an identifier property. A good
example is the [Colors](http://wiki.guildwars2.com/wiki/API:1/colors) API
method. ***{{todo: add link to gw2-api-client's `ColorsList` Javadoc}}***

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