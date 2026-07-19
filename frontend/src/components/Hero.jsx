import "../styles/Hero.css";

function Hero() {
  return (
    <section className="hero">
      <div className="hero-content">

        <h1>Find Your Dream Job</h1>

        <p>
          Search thousands of jobs from top companies across India.
        </p>

        <div className="hero-search">

          <input
            type="text"
            placeholder="Search Job Title"
          />

          <input
            type="text"
            placeholder="Search Location"
          />

          <button>
            Search
          </button>

        </div>

      </div>
    </section>
  );
}

export default Hero;