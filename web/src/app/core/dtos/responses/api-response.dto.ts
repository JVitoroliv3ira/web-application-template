export interface ApiResponseDTO<C> {
  message: string;
  content: C;
  errors: string[];
};

