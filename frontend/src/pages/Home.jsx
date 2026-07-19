import Stats from "../components/Stats";
import { useEffect, useState } from "react";
import Hero from "../components/Hero";
import JobCard from "../components/JobCard";
import { getAllJobs } from "../services/jobService";
import "../styles/Home.css";

function Home() {
  const [jobs, setJobs] = useState([]);
  const [filteredJobs, setFilteredJobs] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchJobs();
  }, []);

  const fetchJobs = async () => {
    try {
      const data = await getAllJobs();
      setJobs(data);
      setFilteredJobs(data);
    } catch (error) {
      console.error("Error fetching jobs:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="home">

      {/* Hero Banner */}
      <Hero />

      {/* Statistics */}
      <Stats />

      {/* Featured Companies */}
      <section className="placeholder-section">
        <h2>Featured Companies</h2>
        <p>Coming Soon...</p>
      </section>

      {/* Latest Jobs */}
      <section className="jobs-section">
        <h2>Latest Jobs</h2>

        {loading ? (
          <p>Loading jobs...</p>
        ) : filteredJobs.length === 0 ? (
          <p>No jobs found.</p>
        ) : (
          <div className="job-list">
            {filteredJobs.map((job) => (
              <JobCard key={job.id} job={job} />
            ))}
          </div>
        )}
      </section>

      {/* Categories */}
      <section className="placeholder-section">
        <h2>Job Categories</h2>
        <p>Coming Soon...</p>
      </section>

      {/* Popular Skills */}
      <section className="placeholder-section">
        <h2>Popular Skills</h2>
        <p>Coming Soon...</p>
      </section>

      {/* Testimonials */}
      <section className="placeholder-section">
        <h2>Testimonials</h2>
        <p>Coming Soon...</p>
      </section>

      {/* Newsletter */}
      <section className="placeholder-section">
        <h2>Newsletter</h2>
        <p>Coming Soon...</p>
      </section>

    </div>
  );
}

export default Home;