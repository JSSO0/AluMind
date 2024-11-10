CREATE TABLE feedback (
  id SERIAL PRIMARY KEY,
  sentiment VARCHAR(255),
  code VARCHAR(255),
  reason VARCHAR(255),
  feedbackOriginal VARCHAR(255)
);