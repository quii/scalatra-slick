package com.quii.slickfun

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
import util.Try
import util.Success
import util.Failure
import net.liftweb.json._
import net.liftweb.json.Serialization.{read, write}

class MyScalatraServlet extends SlickfunStack {

  val connectionString = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
  implicit val database = Database.forURL(connectionString, driver = "org.h2.Driver")
  implicit val formats = DefaultFormats
  createSchema()

  get("/users") {
    response.setContentType("application/json")
    database withSession {
      write(Query(Users).list())
    }
  }

  post("/users"){

    def insertUser(usr: User) = {
      database withSession {
        Users.insert(usr)
        response.setStatus(201)
        "Inserted User"
      }
    }

    def jsonFail(msg: String) = {
      response.setStatus(400)
      s"Failed to insert user: [$msg]"
    }

    parseJson(request.body) match{
      case Success(user) => insertUser(user)
      case Failure(e) => jsonFail(e.getMessage)
    }

  }

  private def parseJson(json: String): Try[User] = Try(parse(json).extract[User])

  private def createSchema() {
    database withSession {
      (Users.ddl).create
      println("Created users table")
    }
  }
  
}

// Oh so complicated domain...
case class User(id: Option[Int], first: String, last: String)

object Users extends Table[User]("users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def first = column[String]("first")
  def last = column[String]("last")
  def * = id.? ~ first ~ last <> (User, User.unapply _)
}
