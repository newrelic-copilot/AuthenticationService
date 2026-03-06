resource "aws_security_group" "authentication_service" {
  name        = "authentication-service-sg"
  description = "Security group for AuthenticationService – restricted access to high-risk ports"
  vpc_id      = var.vpc_id

  # SSH access is restricted to approved administrative networks only.
  # Provide appropriate CIDR values via the trusted_admin_cidr variable.
  ingress {
    description = "SSH from trusted admin network"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = var.trusted_admin_cidr
  }

  # RDP access is restricted to approved administrative networks only.
  # Provide appropriate CIDR values via the trusted_admin_cidr variable.
  ingress {
    description = "RDP from trusted admin network"
    from_port   = 3389
    to_port     = 3389
    protocol    = "tcp"
    cidr_blocks = var.trusted_admin_cidr
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name        = "authentication-service-sg"
    Environment = "production"
    Owner       = "platform-security"
    Purpose     = "Restrict high-risk port access for AuthenticationService"
    ManagedBy   = "terraform"
  }
}
