import React, { useState } from "react";
import CharacterList from "./components/CharacterList";
import "./App.css";

function App() {
  const [selectedCharacter, setSelectedCharacter] = useState(null);

  const selectCharacter = (character) => {
    setSelectedCharacter(character);
  };

  return (
    <>
      <CharacterList selectCharacter={selectCharacter} />

      {selectedCharacter && (
        <div className="character-details">
          <h3>{selectedCharacter.name}</h3>
          <p>{selectedCharacter.species}</p>
          <img src={selectedCharacter.image} alt={selectedCharacter.name} />
        </div>
      )}
    </>
  );
}

export default App;
