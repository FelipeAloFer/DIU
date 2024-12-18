import "./ContadorComponent.css";
import React from "react";

function ContadorComponent({
  contador,
  decreaseContador,
  resetContador,
  increaseContador,
  clase,
}) {
  return (
    <div className="container-counter">
      <div className="counter">
        <h1>{contador}</h1>
      </div>
      <div className="buttons">
        <button className="btn btn-primary" onClick={decreaseContador}>
          -
        </button>
        <button className="btn btn-primary" onClick={resetContador}>
          Reset
        </button>
        <button
          className={`btn btn-primary ${clase}`}
          onClick={increaseContador}
        >
          +
        </button>
      </div>
    </div>
  );
}

export default ContadorComponent;

// import React, { Component } from "react";
// import "./ContadorComponent.css";

// class ContadorComponent extends Component {
//   render() {
//     const {
//       contador,
//       decreaseContador,
//       resetContador,
//       increaseContador,
//       clase,
//     } = this.props;

//     return (
//       <div className="container-counter">
//         <div className="counter">
//           <h1>{contador}</h1>
//         </div>
//         <div className="buttons">
//           <button className="btn btn-primary" onClick={decreaseContador}>
//             -
//           </button>
//           <button className="btn btn-primary" onClick={resetContador}>
//             Reset
//           </button>
//           <button
//             className={`btn btn-primary ${clase}`}
//             onClick={increaseContador}
//           >
//             +
//           </button>
//         </div>
//       </div>
//     );
//   }
// }

// export default ContadorComponent;
