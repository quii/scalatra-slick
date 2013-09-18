import com.quii.slickfun._
import org.scalatra._
import javax.servlet.ServletContext
import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession
class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    context.mount(new MyScalatraServlet, "/*")
  }
}
