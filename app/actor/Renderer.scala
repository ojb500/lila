package lila.app
package actor

import akka.actor._
import play.twirl.api.Html

import lila.game.Pov
import views.{ html => V }

private[app] final class Renderer extends Actor {

  def receive = {

    case lila.tv.actorApi.RenderFeaturedJs(game) =>
      sender ! V.game.bits.featuredJs(Pov first game).body

    case lila.tournament.actorApi.TournamentTable(tours) =>
      sender ! V.tournament.enterable(tours).render

    case lila.simul.actorApi.SimulTable(simuls) =>
      sender ! V.simul.allCreated(simuls).render

    case lila.puzzle.RenderDaily(puzzle, fen, lastMove) =>
      sender ! V.puzzle.bits.daily(puzzle, fen, lastMove).render

    case streams: lila.streamer.LiveStreams.WithTitles =>
      sender ! V.streamer.bits.liveStreams(streams).render
  }
}
