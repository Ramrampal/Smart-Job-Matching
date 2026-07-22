import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllJobs } from "../services/jobService";

function Jobs() {
  const [jobs, setJobs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const loadJobs = async () => {
      try {
        const data = await getAllJobs();
        console.log(data);
        setJobs(data);
      } catch (error) {
        console.error("Error fetching jobs:", error);
      }
    };

    loadJobs();
  }, []);

  return (
    <div
      style={{
        maxWidth: "1000px",
        margin: "40px auto",
        padding: "20px",
      }}
    >
      <h1
        style={{
          textAlign: "center",
          marginBottom: "30px",
        }}
      >
        Available Jobs
      </h1>

      {jobs.length === 0 ? (
        <h2 style={{ textAlign: "center" }}>No Jobs Available</h2>
      ) : (
        jobs.map((job) => (
          <div
            key={job.id}
            style={{
              border: "1px solid #ddd",
              borderRadius: "10px",
              padding: "20px",
              marginBottom: "20px",
              boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
              background: "#fff",
            }}
          >
            <h2>{job.title}</h2>

            <p>
              <strong>Company:</strong> {job.company}
            </p>

            <p>
              <strong>Location:</strong> {job.location}
            </p>

            <p>
              <strong>Salary:</strong> {job.salary}
            </p>

            <p>
              <strong>Job Type:</strong>{" "}
              {job.jobType ? job.jobType : "Not Specified"}
            </p>

            <p>
              <strong>Description:</strong> {job.description}
            </p>

            <div
              style={{
                marginTop: "20px",
              }}
            >
              <button
                onClick={() => navigate(`/jobs/${job.id}`)}
                style={{
                  padding: "10px 18px",
                  marginRight: "10px",
                  border: "none",
                  borderRadius: "5px",
                  background: "#007bff",
                  color: "white",
                  cursor: "pointer",
                }}
              >
                View Details
              </button>

              <button
                onClick={() => alert("Job Applied Successfully!")}
                style={{
                  padding: "10px 18px",
                  border: "none",
                  borderRadius: "5px",
                  background: "green",
                  color: "white",
                  cursor: "pointer",
                }}
              >
                Apply Now
              </button>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default Jobs;