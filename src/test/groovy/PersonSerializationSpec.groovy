import com.example.helloworld.core.Person
import spock.lang.Specification

import static com.yammer.dropwizard.testing.JsonHelpers.*

class PersonSerializationSpec extends Specification {

    Person person

    def setup() {
        person = new Person(0, "Some Dude", "Boss");
    }

    def 'can serialize a person to JSON'() {
        expect:
        asJson(person) == jsonFixture("fixtures/person.json")
    }

    def 'can deserialize a person from JSON'() {
        expect:
        fromJson(jsonFixture('fixtures/person.json'), Person.class) == person
    }

}
