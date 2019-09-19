package comp1110.ass2;

public class TestUtility {

  static final String[] GOOD_PLACEMENTS = {
          "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0",      //  1
          "a6B0b6C0c5A2d1B3e4A5f4C2g2B3h1A2i7D0j7A0k5B0k5C0l3A0l3D0",      //  2
          "a6A0b4A2c3A3d1A3e1C4f4B3g6B2h5D0i5A0j2B0j3C0k2C0k2D0l8C0l8D0",  // 25
          "a4C4b2C4c1B2d7B1e1C6f6A0g4A5h1A0j3B0j7D0k1C0k1D0l6B0l1A0",      // 26
          "a7B1b2C4c1B2d4C4e1C3f4A0g6A1h1A0j3B0j5C0",                      // 49
          "a1B5b2C0c5A2d7B7e5B0f1A6g3A7h5D0i1B0j7A0j7B0k1A0k2B0l3B0l4C0",  // 50
          "a1C6b6A6c2D0d7B1e1A3f2A2g4B2h4A2i7B0j3D0j7D0k3A0l6A0",          // 73
          "a6C4b7A1c2D0d1A0e5B4f1B3g3A3h5A0k1B0k6B0l5A0l3C0",              // 74
          "a1A3b5A4c5C0d3A6e7A1f3C4g1B3h6D0j4B0k8B0k5D0l3C0",              // 97
          "a7A7b3B5c3A0d1A3e5C2f1C4g6B7h4B0k3D0k5D0l6C0"                   // 98
  };

  static final String[] BAD_PIECES = {
          "a7B9",
          "z1A3",
          "a0B1",
          "a9B1",
          "a6F0",
          "A7A7",
          "i3C1"
  };

  static final String[] BAD_PEGS = {
          "i6B0j2B0j1C0j3C0l4B0l5C0",
          "i7D0j7A0k5B0k5C0k3A0l3D0",
          "j3B0j7D0k1C0l1D0l6B0l1A0"
  };

  static final String[] OFF_BOARD = {
          "a7D7",
          "b7C0",
          "c6A0",
          "d1C3",
          "e1D6",
          "f7A6",
          "g3C7",
          "h7A2",
  };

  static final String[] OVERLAP = {
          "a7A7b7A7",
          "b6A0c5A2",
          "c2A3d1A3",
          "d7B1e6C6",
          "e4B3f4A6",
          "f1B6g3A7",
          "g4B2h4D2",
          "a6C4h4D0",
  };

  static final String[] INVALID_PEGS = {
          "a7A7j8A0",
          "b6A0i7A0",
          "c2A3i2C0",
          "d7B1j8B0",
          "e4B3j5B0",
          "f1B6k3B0",
          "g4B2k6D0",
          "h4D0l6D0",
  };

  static final String[] NO_VIABLE = {
          "a7A7c1A3d2A6e2C3f3C2g4A7h6D0i6A0j2B0j1C0k3C0l4B0l5C0",
          "b6C0c5A2d1B3e4A5f4C2g2B3h1A2i7D0j7B0k5B0k5C0l3A0l3D0",
          "a6A0b4A2d1A3e1C4f3B5g6B2h5D0i5A0j2B0k2C0k2D0l8C0l8D0",
          "a4C4b2C6c1B2e1C6f6A6g4A5h1A0j3B0j8B0k1C0k1D0l6B0l1A0",
          "a7B1b2C6c1A1d4C4f4A6g6A1h2A1j3B0j5C0",
          "a1B5b2C0c5A2d7B7e5B0g3A7h5D0i1B0j7A0j7B0k1A0k2A0l3B0l4C0",
          "a1C6b6A6c2D0d7B1e1A6f2A2h5A1i7B0j3D0j7D0k3A0l6A0",
          "a6C4b7A3c2D2d1A0e5B4f1B3g3A3k1B0k6B0l6A0l3C0",
  };

  static final String[] CONSTRAINED = {
          "b6A7c1A3f3C2",
          "b6C0c5A2g2B3",
          "b4A2c3A3h5D0",
          "b2C4d7B1",
          "c1B2d4C4",
          "a1B5d7B7",
          "b6A6c2D0f2A2",
          "b7A1g3A3",
          "c5C0d3A6g1B3",
          "c3A0f1C4g6B7"
  };


  static final String[][] SINGLE= {
          {
                  "c1A3d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0",      //  1
                  "c5A2d1B3e4A5f4C2g2B3h1A2i7D0j7A0k5B0k5C0l3A0l3D0",      //  2
                  "c3A3d1A3e1C4f4B5g6B2h5D0i5A0j2B0j3C0k2C0k2D0l8C0l8D0",  // 25
                  "c1B2d7B1e1C6f6A6g4A5h1A0j3B0j7D0k1C0k1D0l6B0l1A0",      // 26
                  "c1B2d4C4e1C3f4A6g6A1h1A0j3B0j5C0",                      // 49
                  "c5A2d7B7e5B0f1A6g3A7h5D0i1B0j7A0j7B0k1A0k2B0l3B0l4C0",  // 50
                  "c2D0d7B1e1A6f2A2g4B2h4A2i7B0j3D0j7D0k3A0l6A0",          // 73
                  "c2D2d1A0e5B4f1B3g3A3h5A0k1B0k6B0l5A0l3C0",              // 74
                  "c5C0d3A6e7A1f3C4g1B3h6D0j4B0k8B0k5D0l3C0",              // 97
                  "c3A0d1A3e5C2f1C4g6B7h4B2k3D0k5D0l6C0"                   // 98
          },
          {
                  "d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0",      //  1
                  "d1B3e4A5f4C2g2B3h1A2i7D0j7A0k5B0k5C0l3A0l3D0",      //  2
                  "d1A3e1C4f4B5g6B2h5D0i5A0j2B0j3C0k2C0k2D0l8C0l8D0",  // 25
                  "d7B1e1C6f6A6g4A5h1A0j3B0j7D0k1C0k1D0l6B0l1A0",      // 26
                  "d4C4e1C3f4A6g6A1h1A0j3B0j5C0",                      // 49
                  "d7B7e5B0f1A6g3A7h5D0i1B0j7A0j7B0k1A0k2B0l3B0l4C0",  // 50
                  "d7B1e1A6f2A2g4B2h4A2i7B0j3D0j7D0k3A0l6A0",          // 73
                  "d1A0e5B4f1B3g3A3h5A0k1B0k6B0l5A0l3C0",              // 74
                  "d3A6e7A1f3C4g1B3h6D0j4B0k8B0k5D0l3C0",              // 97
                  "d1A3e5C2f1C4g6B7h4B2k3D0k5D0l6C0"                   // 98
          },
          {
                  "e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0",      //  1
                  "e4A5f4C2g2B3h1A2i7D0j7A0k5B0k5C0l3A0l3D0",      //  2
                  "e1C4f4B5g6B2h5D0i5A0j2B0j3C0k2C0k2D0l8C0l8D0",  // 25
                  "e1C6f6A6g4A5h1A0j3B0j7D0k1C0k1D0l6B0l1A0",      // 26
                  "e1C3f4A6g6A1h1A0j3B0j5C0",                      // 49
                  "e5B0f1A6g3A7h5D0i1B0j7A0j7B0k1A0k2B0l3B0l4C0",  // 50
                  "e1A6f2A2g4B2h4A2i7B0j3D0j7D0k3A0l6A0",          // 73
                  "e5B4f1B3g3A3h5A0k1B0k6B0l5A0l3C0",              // 74
                  "e7A1f3C4g1B3h6D0j4B0k8B0k5D0l3C0",              // 97
                  "e5C2f1C4g6B7h4B2k3D0k5D0l6C0"                   // 98
          },
  };

  static final String[][] MULTI = {
          { "c1A3d2A6e2C3f3C2g4A7h6D0j2B0j1C0k3C0l4B0l5C0", "a7A7b6A5c1A3d2A6e2C3f3C2g4A7h6D0", "a6A0b6B0c1A3d2A6e2C3f3C2g4A7h6D0"},
          { "a6B0b6C0c5A2e4A5f4C2h1A2i7D0j7A0k5B0k5C0l3A0", "a6B0b6C0c5A2d1B3e4A5f4C2g2B3h1A2", "a6B0b6C0c5A2d1B5e4A5f4C2g2B5h1A2"},
  };
}
