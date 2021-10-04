import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({teamName, match}) => {

  if(!match) return null;

  const otherTeam = match.team1 === teamName ? match.team2: match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;

  return (
    <div className="MatchDetailCard">
        <h2>Match Details</h2>
        <p>vs <Link to={otherTeamRoute}> {otherTeam} </Link></p>
        <p>Played on {match.date} at Venue {match.city}</p>
        <p>{match.winner} won by {match.resultMargin} {match.result}</p>
    </div>
  );
}
