package test

import org.openjdk.jmh.annotations.Benchmark

import scala.io.Source

/**
  * Based on [[https://github.com/ktoso/sbt-jmh/blob/master/sbt-jmh-tester/src/main/scala/test/TestBenchmark.scala]]
  */
class TestBenchmark {
  import TestBenchmark._

  @Benchmark
  def range(): Int =
    1.to(100000)
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark
  def iterator(): Int =
    Iterator.from(1)
      .takeWhile(_ < 100000)
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark
  def stream(): Int =
    Stream.from(1)
      .takeWhile(_ < 100000)
      .filter(_ % 2 == 0)
      .count(_.toString.length == 4)

  @Benchmark
  def readFromFile(): Int =
    helloFile.getLines()
      .map(_.length)
      .sum
}

object TestBenchmark {
  val helloFile = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("hello.txt"))
}
