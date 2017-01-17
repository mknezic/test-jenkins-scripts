package org.foo
class Utilities implements Serializable {
  def hello(String name = 'alien') {
    echo "hello, ${name}."
  }
}
