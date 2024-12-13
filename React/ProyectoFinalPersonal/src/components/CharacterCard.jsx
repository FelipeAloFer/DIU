import React from "react";

function CharacterCard(props) {
  const { character, selectCharacter } = props;

  return (
    <li className="character-card" onClick={() => selectCharacter(character)}>
      <h2 className="text">{character.name}</h2>
      <img
        src={character.image}
        alt="character img"
        className="character-img"
      />
    </li>
  );
}

export default CharacterCard;
