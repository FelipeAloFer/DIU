import { useState, useEffect } from "react";
import CharacterCard from "./CharacterCard"; // Asegúrate de importar el componente CharacterCard

function CharacterList(props) {
  const [characters, setCharacters] = useState([]);

  useEffect(() => {
    getCharacters();
  }, []);

  const fetchCharacters = async (index) => {
    const response = await fetch(
      `https://rickandmortyapi.com/api/character/?page=${index}`
    );
    const data = await response.json();
    return data;
  };

  const getCharacters = async () => {
    const charArray = [];
    for (let i = 1; i <= 20; i++) {
      const data = await fetchCharacters(i);
      charArray.push(...data.results);
    }
    setCharacters(charArray);
  };

  const characterCard = characters
    .filter((character) => character.image) // Filtra solo aquellos personajes que tienen una imagen
    .map((character) => {
      return (
        <CharacterCard
          key={character.id}
          character={character}
          selectCharacter={props.selectCharacter}
        />
      );
    });

  return (
    <div>
      <ul className="character-list">{characterCard}</ul>
    </div>
  );
}

export default CharacterList;
