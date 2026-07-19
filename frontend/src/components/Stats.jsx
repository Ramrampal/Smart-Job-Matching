import "../styles/Stats.css";

function Stats() {
  const stats = [
    {
      number: "5000+",
      title: "Jobs",
    },
    {
      number: "300+",
      title: "Companies",
    },
    {
      number: "10000+",
      title: "Candidates",
    },
    {
      number: "98%",
      title: "Success Rate",
    },
  ];

  return (
    <section className="stats">

      <div className="stats-container">

        {stats.map((item, index) => (
          <div className="stat-card" key={index}>

            <h2>{item.number}</h2>

            <p>{item.title}</p>

          </div>
        ))}

      </div>

    </section>
  );
}

export default Stats;