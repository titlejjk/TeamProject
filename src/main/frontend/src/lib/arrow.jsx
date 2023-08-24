import React from 'react';

export function Arrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{
        ...style,
        display: "block",
        filter: " invert(27%) sepia(24%) saturate(1333%) hue-rotate(333deg) brightness(90%) contrast(81%)",
        zoom: "1.6",
        top: "40%"       
      }}
      onClick={onClick}
    />
  );
}

