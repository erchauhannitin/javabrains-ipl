import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';

export const MatchPage = () => {

  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
      const data = await response.json();
      console.log(data);
      setMatches(data);
    };
    fetchMatches();
  }, []);

  return (
    <div className="MatchPage">
         <h2>Match Page</h2>
         {matches.map(match => <MatchDetailCard teamName={teamName} match={match} />)}
    </div>
  );
}
